package com.javadiscord.jdi.example;

import java.awt.*;
import java.util.Optional;

import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.CommandOptionChoice;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;
import com.javadiscord.jdi.core.models.message.embed.Embed;

public class ExampleSlashCommand {

    @SlashCommand(
        name = "quiz", description = "A fun Java quiz", options = {
            @CommandOption(
                name = "q1", description = "What is an Integer?", type = CommandOptionType.STRING, choices = {
                    @CommandOptionChoice(
                        name = "option1", value = "An object that represents a number"
                    ),
                    @CommandOptionChoice(name = "option2", value = "A class used to store objects"),
                    @CommandOptionChoice(name = "option3", value = "A primitive data type")
                }
            ),
            @CommandOption(
                name = "q2", description = "In which package is the List interface defined?", type = CommandOptionType.STRING, choices = {
                    @CommandOptionChoice(name = "option1", value = "java.util"),
                    @CommandOptionChoice(name = "option2", value = "java.lang"),
                    @CommandOptionChoice(name = "option3", value = "java.io")
                }
            ),
            @CommandOption(
                name = "q3", description = "What does JVM stand for?", type = CommandOptionType.STRING, choices = {
                    @CommandOptionChoice(name = "option1", value = "Java Virtual Machine"),
                    @CommandOptionChoice(name = "option2", value = "Java Verified Module"),
                    @CommandOptionChoice(name = "option3", value = "Java Variable Method")
                }
            ),
            @CommandOption(
                name = "q4", description = "Is a String a primitive data type?", type = CommandOptionType.STRING, choices = {
                    @CommandOptionChoice(name = "option1", value = "Yes"),
                    @CommandOptionChoice(name = "option2", value = "No")
                }
            ),
            @CommandOption(
                name = "q5", description = "Which of the following is not a Java keyword?", type = CommandOptionType.STRING, choices = {
                    @CommandOptionChoice(name = "option1", value = "static"),
                    @CommandOptionChoice(name = "option2", value = "void"),
                    @CommandOptionChoice(name = "option3", value = "main"),
                    @CommandOptionChoice(name = "option4", value = "private")
                }
            )
        }
    )
    public void handle(SlashCommandEvent event, Guild guild) {
        event.deferReply();

        Optional<ApplicationCommandOption> q1 = event.option("q1");
        Optional<ApplicationCommandOption> q2 = event.option("q2");
        Optional<ApplicationCommandOption> q3 = event.option("q3");
        Optional<ApplicationCommandOption> q4 = event.option("q4");
        Optional<ApplicationCommandOption> q5 = event.option("q5");

        StringBuilder feedback = new StringBuilder();

        q1.ifPresent(answer -> {
            if (answer.valueAsString().equals("option1")) {
                feedback.append("Q1: Correct!\n");
            } else {
                feedback.append("Q1: Incorrect\n");
            }
        });

        q2.ifPresent(answer -> {
            if (answer.valueAsString().equals("option1")) {
                feedback.append("Q2: Correct!\n");
            } else {
                feedback.append("Q2: Incorrect\n");
            }
        });

        q3.ifPresent(answer -> {
            if (answer.valueAsString().equals("option1")) {
                feedback.append("Q3: Correct!\n");
            } else {
                feedback.append("Q3: Incorrect\n");
            }
        });

        q4.ifPresent(answer -> {
            if (answer.valueAsString().equals("option2")) {
                feedback.append("Q4: Correct!\n");
            } else {
                feedback.append("Q4: Incorrect\n");
            }
        });

        q5.ifPresent(answer -> {
            if (answer.valueAsString().equals("option3")) {
                feedback.append("Q5: Correct!\n");
            } else {
                feedback.append("Q5: Incorrect\n");
            }
        });

        int score = score(feedback.toString());

        feedback.append("Your score: ").append(score).append("/5");

        if (score == 5) {
            feedback.append("\nCongratulations! You you all the questions right!\n");
        }

        Embed embed =
            new Embed.Builder()
                .color(Color.CYAN)
                .description(feedback.toString())
                .build();

        event.reply(embed)
            .onSuccess(System.out::println)
            .onError(System.err::println);
    }

    private static int score(String str) {
        String word = "Correct";
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }

}
