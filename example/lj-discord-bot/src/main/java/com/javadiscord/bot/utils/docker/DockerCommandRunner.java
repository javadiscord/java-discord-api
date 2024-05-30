package com.javadiscord.bot.utils.docker;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;

public class DockerCommandRunner {
    private final DockerClient dockerClient;

    public DockerCommandRunner(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public OutputStream sendCommand(Session session, String command) throws InterruptedException {
        session.updateHistory(command);

        OutputStream output = new ByteArrayOutputStream();

        ExecCreateCmdResponse execCreateCmdResponse =
            dockerClient
                .execCreateCmd(session.getContainerId())
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withCmd("bash", "-c", command.trim())
                .exec();

        dockerClient
            .execStartCmd(execCreateCmdResponse.getId())
            .exec(new ExecStartResultCallback(output, output))
            .awaitCompletion();

        return output;
    }
}
