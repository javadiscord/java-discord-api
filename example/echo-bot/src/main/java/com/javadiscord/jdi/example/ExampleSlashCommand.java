package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.CommandOptionChoice;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;

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
        ApplicationCommandOption q1 = event.option("q1");
        ApplicationCommandOption q2 = event.option("q2");
        ApplicationCommandOption q3 = event.option("q3");
        ApplicationCommandOption q4 = event.option("q4");
        ApplicationCommandOption q5 = event.option("q5");

        // Check answers and prepare feedback
        StringBuilder feedback = new StringBuilder();
        int score = 0;

        if (q1.toString().equals("option1")) {
            score++;
            feedback.append("Q1: Correct!\n");
        } else {
            feedback.append("Q1: Incorrect\n");
        }

        if (q2.toString().equals("option1")) {
            score++;
            feedback.append("Q2: Correct!\n");
        } else {
            feedback.append("Q1: Incorrect\n");
        }

        if (q3.toString().equals("option1")) {
            score++;
            feedback.append("Q3: Correct!\n");
        } else {
            feedback.append("Q1: Incorrect\n");
        }

        if (q4.toString().equals("option2")) {
            score++;
            feedback.append("Q4: Correct!\n");
        } else {
            feedback.append("Q1: Incorrect\n");
        }

        if (q5.toString().equals("option3")) {
            score++;
            feedback.append("Q5: Correct!\n");
        } else {
            feedback.append("Q1: Incorrect\n");
        }

        feedback.append("Your score: ").append(score).append("/5");

        if (score == 5) {
            feedback.append("\nCongratulations! You you all the questions right!\n");
        }

        guild.channel().createMessage(
            new CreateMessageBuilder(event.channel().id()).content(feedback.toString())
        );
    }
}
