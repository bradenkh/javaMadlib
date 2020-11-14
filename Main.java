import java.io.*;
import java.util.*;

class Main {
  public static String getFileName(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter File Name or type default: ");
    String name = scan.next();
    if (name.equals("default")) {
      name = "madlib.txt";
    }
    return name;
  }

  public static List<String> getStory(String fileName){
    List<String> tempStory = new ArrayList<>();
    try {
      File myFile = new File(fileName); // open filestream
      Scanner readLine = new Scanner(myFile);
        while (readLine.hasNextLine()) { // read the line in
          Scanner readWord = new Scanner(readLine.nextLine());
          while(readWord.hasNext()) { // read the words from the line in
            tempStory.add(readWord.next());
          }
        }
    } catch (FileNotFoundException e) { // check to see if the file was good
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return tempStory; //send story back to main
  }

  public static String getWord(String rawPrompt) {
    // output the prompt
    // get the word and put it in the
    System.out.print(rawPrompt.charAt(1));
    for (int i = 2; i < rawPrompt.length(); i++) {
      switch(rawPrompt.charAt(i)) {
          case '_':
            System.out.print(" ");
            break;
          default:
            System.out.print(rawPrompt.charAt(i));
      }
      ;
    }
    System.out.print(": ");
    Scanner scan = new Scanner(System.in);
    String word = scan.next();
    return word;  
    }

  public static List<String> findPrompts(List<String> tempStory) {
    for(int i = 0; i < tempStory.size(); i++) {
      if (tempStory.get(i).charAt(0) == ':') { // look for colons
        if ((int)tempStory.get(i).charAt(1) > 65) { // makes sure the prompt starts with a letter
            tempStory.set(i, getWord(tempStory.get(i)));
        }
      };
    }
    
    return tempStory;
  }

  public static void display(List<String> output) {
    for(int i = 0; i < output.size(); i++) {
      if (output.get(i).charAt(0) != ':') {
        System.out.print(output.get(i) + " ");
      }
      else {
        System.out.println();
      }
      // switch(output.get(i).charAt(1)) {
      //     case '.':
      //       System.out.print(".");
      //       break;
      //     case '!':
      //       System.out.println();
      //       break;
      //     case '<':
      //       System.out.print("\"");
      //       break;
      //     case '>':
      //       System.out.print("\"");
      //       break;
      //     default:
      //       System.out.print(output.get(i));
      // } // end of switch

    } // end of for
    return;
  }

  public static void main(String[] args) {
    String fileName = getFileName();
    List<String> story = getStory(fileName);
    story = findPrompts(story);
    display(story);
  }
}