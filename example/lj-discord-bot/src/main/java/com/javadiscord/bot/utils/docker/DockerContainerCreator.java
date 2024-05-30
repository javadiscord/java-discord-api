package com.javadiscord.bot.utils.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;

public class DockerContainerCreator {
    private final DockerClient dockerClient;

    public DockerContainerCreator(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public CreateContainerResponse createContainerStarted(
        String name,
        String image,
        long memoryLimit,
        long memorySwapLimit,
        int cpuShares,
        long cpuPeriod,
        long cpuQuota
    ) {

        CreateContainerResponse createContainerResponse =
            createContainer(
                name, image, memoryLimit, memorySwapLimit, cpuShares, cpuPeriod, cpuQuota
            );

        startContainer(createContainerResponse.getId());

        return createContainerResponse;
    }

    public CreateContainerResponse createContainer(
        String name,
        String image,
        long memoryLimit,
        long memorySwapLimit,
        int cpuShares,
        long cpuPeriod,
        long cpuQuota
    ) {

        HostConfig hostConfig =
            HostConfig.newHostConfig()
                .withAutoRemove(true)
                .withInit(true)
                .withMemory(memoryLimit)
                .withMemorySwap(memorySwapLimit)
                .withCpuShares(cpuShares)
                .withCpuPeriod(cpuPeriod)
                .withCpuQuota(cpuQuota);

        return dockerClient
            .createContainerCmd(image)
            .withHostConfig(hostConfig)
            .withStdinOpen(true)
            .withName(name)
            .exec();
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }
}
