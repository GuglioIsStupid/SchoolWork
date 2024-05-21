package NestedIfStatements;

// Guglio - 2024/03/18
// Choose your own adventure game based off a Haunted House

import java.util.Scanner;

public class ChooseYourOwnAdventureGame {
    static boolean started = false;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if (!started) {
            System.out.println("Welcome to the Haunted House! You must escape!");
            System.out.println("In this Haunted House, you will be presented with choices that will lead you to your escape or your doom.\n");
            System.out.println("You may be stuck in loops depending on your choices, but all choices lead to a way out.\n");
            started = true;
        }
        System.out.println("You are in a room with two doors. Which door do you choose? (1 or 2)");
        
        int doorChoice = input.nextInt();
        if (doorChoice == 1) {
            System.out.println("You chose door 1. You are now in a room with a ghost. Do you run or hide? (run or hide)");
            String ghostChoice = input.next();
            if (ghostChoice.equals("run")) {
                System.out.println("You ran away from the ghost and found the exit! You win!");
            } else if (ghostChoice.equals("hide")) {
                System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                String doorChoice2 = input.next();
                if (doorChoice2.equals("left")) {
                    System.out.println("You chose the door to the left coming face to face with a vampire in the kitchen. There is a knife on the counter. Do you grab the knife or run? (grab or run)");
                    String vampireChoice = input.next();
                    if (vampireChoice.equals("grab")) {
                        System.out.println("You grabbed the knife and killed the vampire. Causing the other monsters to flee. You win!");
                    } else if (vampireChoice.equals("run")) {
                        System.out.println("You run from the vampire and find yourself in the basement. There is a door to the left and a door to the right and a door in front of you. Which door do you choose? (left, right, or front)");
                        String doorChoice3 = input.next();
                        if (doorChoice3.equals("left")) {
                            System.out.println("You chose the door to the left and come across some stairs and a door to the left. Do you go up the stairs or go through the door? (stairs or door)");
                            String stairsChoice = input.next();
                            if (stairsChoice.equals("stairs")) {
                                System.out.println("You go up the stairs and come across a door to the left and a door to the right. Which door do you choose? (left or right)");
                                String doorChoice4 = input.next();
                                if (doorChoice4.equals("left")) {
                                    System.out.println("You chose the door to the left and come across a room with a werewolf. Do you fight or run? (fight or run)");
                                    String werewolfChoice = input.next();
                                    if (werewolfChoice.equals("fight")) {
                                        System.out.println("You fought the werewolf successfully, causing the other monsters to flee. You win!");
                                    } else if (werewolfChoice.equals("run")) {
                                        System.out.println("You ran from the werewolf back out the door and up the stairs. You now have a choice to go left or right. Which way do you go? (left or right)");
                                    }
                                } else if (doorChoice4.equals("right")) {
                                    System.out.println("You chose the door to the right and come across a room with a ghost. Do you run or hide? (run or hide)");
                                    String ghostChoice2 = input.next();
                                    if (ghostChoice2.equals("run")) {
                                        System.out.println("You ran away from the ghost and found the exit! You win!");
                                    } else if (ghostChoice2.equals("hide")) {
                                        System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                        String doorChoice5 = input.next();
                                        // Left = outside door = win
                                        if (doorChoice5.equals("left")) {
                                            System.out.println("You chose the door to the left and found the exit! You win!");
                                        } else if (doorChoice5.equals("right")) {
                                            main(args);
                                        }
                                    }
                                }
                            } else if (stairsChoice.equals("door")) {
                                System.out.println("You go through the door and find yourself in a room with a werewolf. Do you fight or run? (fight or run)");
                                String werewolfChoice = input.next();
                                if (werewolfChoice.equals("fight")) {
                                    System.out.println("You fought the werewolf successfully, causing the other monsters to flee. You win!");
                                } else if (werewolfChoice.equals("run")) {
                                    System.out.println("You ran from the werewolf back out the door and up the stairs. You now have a choice to go left or right. Which way do you go? (left or right)");
                                    String doorChoice4 = input.next();
                                    if (doorChoice4.equals("left")) {
                                        System.out.println("You chose the door to the left and come across a room with a ghost. Do you run or hide? (run or hide)");
                                        String ghostChoice2 = input.next();
                                        if (ghostChoice2.equals("run")) {
                                            System.out.println("You ran away from the ghost and found the exit! You win!");
                                        } else if (ghostChoice2.equals("hide")) {
                                            System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                            String doorChoice5 = input.next();
                                            // Left = outside door = win
                                            if (doorChoice5.equals("left")) {
                                                System.out.println("You chose the door to the left and found the exit! You win!");
                                            } else if (doorChoice5.equals("right")) {
                                                // start of loop causing you to be stuck in once more
                                                main(args);
                                            }
                                        }
                                    } else if (doorChoice4.equals("right")) {
                                        System.out.println("You chose the door to the right and come across a room with a ghost. Do you run or hide? (run or hide)");
                                        String ghostChoice2 = input.next();
                                        if (ghostChoice2.equals("run")) {
                                            System.out.println("You ran away from the ghost and found the exit! You win!");
                                        } else if (ghostChoice2.equals("hide")) {
                                            System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                            String doorChoice5 = input.next();
                                            // Left = outside door = win
                                            if (doorChoice5.equals("left")) {
                                                System.out.println("You chose the door to the left and found the exit! You win!");
                                            } else if (doorChoice5.equals("right")) {
                                                // start of loop causing you to be stuck in once more
                                                main(args);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (doorChoice == 2) {
            System.out.println("You chose door 2. You are now in a room with a vampire. Do you run or hide? (run or hide)");
            String vampireChoice = input.next();
            if (vampireChoice.equals("run")) {
                System.out.println("You ran away from the vampire and found the exit! You win!");
            } else if (vampireChoice.equals("hide")) {
                System.out.println("You hid from the vampire by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                String doorChoice2 = input.next();
                if (doorChoice2.equals("left")) {
                    System.out.println("You chose the door to the left coming face to face with a ghost in the kitchen. There is a knife on the counter. Do you grab the knife or run? (grab or run)");
                    String ghostChoice = input.next();
                    if (ghostChoice.equals("grab")) {
                        System.out.println("You grabbed the knife and killed the ghost. Causing the other monsters to flee. You win!");
                    } else if (ghostChoice.equals("run")) {
                        System.out.println("You run from the ghost and find yourself in the basement. There is a door to the left and a door to the right and a door in front of you. Which door do you choose? (left, right, or front)");
                        String doorChoice3 = input.next();
                        if (doorChoice3.equals("left")) {
                            System.out.println("You chose the door to the left and come across some stairs and a door to the left. Do you go up the stairs or go through the door? (stairs or door)");
                            String stairsChoice = input.next();
                            if (stairsChoice.equals("stairs")) {
                                System.out.println("You go up the stairs and come across a door to the left and a door to the right. Which door do you choose? (left or right)");
                                String doorChoice4 = input.next();
                                if (doorChoice4.equals("left")) {
                                    System.out.println("You chose the door to the left and come across a room with a werewolf. Do you fight or run? (fight or run)");
                                    String werewolfChoice = input.next();
                                    if (werewolfChoice.equals("fight")) {
                                        System.out.println("You fought the werewolf successfully, causing the other monsters to flee. You win!");
                                    } else if (werewolfChoice.equals("run")) {
                                        System.out.println("You ran from the werewolf back out the door and up the stairs. You now have a choice to go left or right. Which way do you go? (left or right)");
                                    }
                                } else if (doorChoice4.equals("right")) {
                                    System.out.println("You chose the door to the right and come across a room with a ghost. Do you run or hide? (run or hide)");
                                    String ghostChoice2 = input.next();
                                    if (ghostChoice2.equals("run")) {
                                        System.out.println("You ran away from the ghost and found the exit! You win!");
                                    } else if (ghostChoice2.equals("hide")) {
                                        System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                        String doorChoice5 = input.next();
                                        // Left = outside door = win
                                        if (doorChoice5.equals("left")) {
                                            System.out.println("You chose the door to the left and found the exit! You win!");
                                        } else if (doorChoice5.equals("right")) {
                                            main(args);
                                        }
                                    }
                                }
                            } else if (stairsChoice.equals("door")) {
                                System.out.println("You go through the door and find yourself in a room with a werewolf. Do you fight or run? (fight or run)");
                                String werewolfChoice = input.next();
                                if (werewolfChoice.equals("fight")) {
                                    System.out.println("You fought the werewolf successfully, causing the other monsters to flee. You win!");
                                } else if (werewolfChoice.equals("run")) {
                                    System.out.println("You ran from the werewolf back out the door and up the stairs. You now have a choice to go left or right. Which way do you go? (left or right)");
                                    String doorChoice4 = input.next();
                                    if (doorChoice4.equals("left")) {
                                        System.out.println("You chose the door to the left and come across a room with a ghost. Do you run or hide? (run or hide)");
                                        String ghostChoice2 = input.next();
                                        if (ghostChoice2.equals("run")) {
                                            System.out.println("You ran away from the ghost and found the exit! You win!");
                                        } else if (ghostChoice2.equals("hide")) {
                                            System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                            String doorChoice5 = input.next();
                                            // Left = outside door = win
                                            if (doorChoice5.equals("left")) {
                                                System.out.println("You chose the door to the left and found the exit! You win!");
                                            } else if (doorChoice5.equals("right")) {
                                                // start of loop causing you to be stuck in once more
                                                main(args);
                                            }
                                        }
                                    } else if (doorChoice4.equals("right")) {
                                        System.out.println("You chose the door to the right and come across a room with a ghost. Do you run or hide? (run or hide)");
                                        String ghostChoice2 = input.next();
                                        if (ghostChoice2.equals("run")) {
                                            System.out.println("You ran away from the ghost and found the exit! You win!");
                                        } else if (ghostChoice2.equals("hide")) {
                                            System.out.println("You hid from the ghost by running into the next room. There is a door to the left and a door to the right. Which door do you choose? (left or right)");
                                            String doorChoice5 = input.next();
                                            // Left = outside door = win
                                            if (doorChoice5.equals("left")) {
                                                System.out.println("You chose the door to the left and found the exit! You win!");
                                            } else if (doorChoice5.equals("right")) {
                                                // start of loop causing you to be stuck in once more
                                                main(args);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
