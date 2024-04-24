import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class texteditor extends JFrame implements ActionListener{
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar =new JMenuBar();
    JMenu filemenu ,editmenu;
    JMenuItem newfileiteam,openiteam,saveiteam,exititeam;
    JMenuItem  cut,copy,paste,sellectall;


    texteditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TextEditor");
        this.setSize(500,500);
        this.setLayout(new FlowLayout() );
        this.setVisible(true);

        // Text area ---------------------------------------
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial",Font.PLAIN,20));

        //scrollpane -----------------------------------------
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);



        //menu bar ----------------

        //file
        filemenu = new JMenu("File");
        // iteams
        newfileiteam = new JMenuItem("New File");
        openiteam = new JMenuItem("Open");
        saveiteam = new JMenuItem("Save");
        exititeam = new JMenuItem("Exit");

        //actionlistener of iteams
        newfileiteam.addActionListener(this);
        openiteam.addActionListener(this);
        saveiteam.addActionListener(this);
        exititeam.addActionListener(this);


        //edit
        editmenu = new JMenu("Edit");

        //iteams
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste =  new JMenuItem("Paste");
        sellectall = new JMenuItem("Sellect All");

        //actinlistners of iteams
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        sellectall.addActionListener(this);



        //file iteam add
        filemenu.add(newfileiteam);
        filemenu.add(openiteam);
        filemenu.add(saveiteam);
        filemenu.add(exititeam);

        //edit iteam add
        editmenu.add(cut);
        editmenu.add(copy);
        editmenu.add(paste);
        editmenu.add(sellectall);

        //menubar menu add
        menuBar.add(filemenu);
        menuBar.add(editmenu);

        this.setJMenuBar(menuBar);

    }




    @Override
    public void actionPerformed(ActionEvent e) {

       /* if(e.getSource()==openiteam){
            //open a file chosser
            JFileChooser fileChooser = new JFileChooser(".");
            //fileChooser.setCurrentDirectory((new File(".")));

            int response =  fileChooser.showSaveDialog(null);

            //if we click on open button
            if(response == JFileChooser.APPROVE_OPTION){
                File file;
                Scanner filein = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    filein = new Scanner(file);
                    if(file.isFile()) {
                        while(filein.hasNextLine()) {
                            String line = filein.nextLine()+"\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                finally {
                    filein.close();
                }
            }
        }*/

        if(e.getSource()==openiteam){
            //open file chooser
            JFileChooser fileChooser = new JFileChooser(".");

            int chooseoption = fileChooser.showOpenDialog(null);

            //if we clicked on open
            if(chooseoption==JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();

                try {
                    //intialization file reader
                    FileReader fileReader = new FileReader(filepath);
                    //bufferreade
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String input = "", output = "";
                    //read the file line by line
                    while ((input = bufferedReader.readLine()) != null) {
                        output += input + "\n";
                    }
                    //output the file in textarea
                    textArea.setText(output);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

       /* if(e.getSource()==saveiteam){

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                File file ;
                PrintWriter fileout = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileout = new PrintWriter(file);
                }catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }
                finally {
                    fileout.close();
                }
            }
        }*/


        if(e.getSource()==saveiteam){
            //open file chooser
            JFileChooser fileChooser = new JFileChooser(".");

            int chooseoption = fileChooser.showSaveDialog(null);

            //if we clicked on save
            if(chooseoption==JFileChooser.APPROVE_OPTION) {

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    //intialization file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //buffer writer
                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(e.getSource()==newfileiteam){
                new texteditor();
        }


        if(e.getSource()==exititeam){
            System.exit(0);
        }


        if(e.getSource()==cut){
            textArea.cut();
        }

        if(e.getSource()==copy){
            textArea.copy();
        }

        if(e.getSource()==paste){
            textArea.paste();
        }

        if(e.getSource()==sellectall){
            textArea.selectAll();
        }

    }
}
