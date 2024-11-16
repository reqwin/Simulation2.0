package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ReadmeTextController implements Controller {

    private String fileName;
    private InputStream inputStream;
    private BufferedReader reader;

    public ReadmeTextController() {
        this.fileName = "Readme.txt";
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (NullPointerException e) {
            throw new LoaderWordException("Call constructor "
                    + " of "
                    + this.getClass()
                    + " : filename '"
                    + fileName
                    + "' may be uncorrect");
        }
    }

    @Override
    public void start() throws IOException {

        displayFirstSentenceOfReadme();
        displayTextOfReadme();
        System.out.println();

    }

    private void displayTextOfReadme() throws IOException {
        String str = "";
        int c;

        while((c=reader.read())!=-1){
            if(c != ' ' && c != '\n' && c != '.' && c != ',' && c != ':'){
                str += (char)c;
            }else{
                if(Pattern.matches("[х-хХ-Х]ищник.*", str)){
                    ColorPrinter.printRedText(str);
                }else if(Pattern.matches("[т-тТ-Т]равоядн.*", str)){
                    ColorPrinter.printGreenText(str);
                }else if(Pattern.matches("[р-рР-Р]астени.*", str)) {
                    ColorPrinter.printYellowText(str);
                }else if(Pattern.matches("[п-пП-П]рограмм.*", str)) {
                    ColorPrinter.printCyanText(str);
                }else if(Pattern.matches("[н-нН-Н]астро.*", str)) {
                    ColorPrinter.printPurpleText(str);
                }else if(Pattern.matches("[c-сC-С]имуляц.*", str)) {
                    ColorPrinter.printBlueText(str);
                }else{
                    System.out.print(str);
                }
                System.out.print((char)c);
                str = "";
            }
        }
        System.out.println(str);
    }


    private void displayFirstSentenceOfReadme() throws IOException {
        String str = "";
        int c;

        while((c=reader.read())!=-1){
            str += (char)c;
            if(c == '.' || c == '\n'){
                ColorPrinter.printYellowBackgroundText(str);
                break;
            }
        }
    }


}
