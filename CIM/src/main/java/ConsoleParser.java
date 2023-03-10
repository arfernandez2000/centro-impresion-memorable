import org.apache.commons.cli.*;

/**
 * Parsea los argumentos obtenidos de la consola.
 * Source: <a href="https://opensource.com/article/21/8/java-commons-cli">Parse command options in Java with commons-cli</a>
 */
public class ConsoleParser {

    public static String staticFile;
    public static String dynamicFile;

    private static Options createOptions(){
        Options options = new Options();

        Option staticFile = Option.builder("staticFile")
                .hasArg()
                .required(true)
                .desc("Path al archivo de valores estáticos").build();
        options.addOption(staticFile);

        Option dynamicFile = Option.builder("dynamicFile")
                .hasArg()
                .required(true)
                .desc("Path al archivo de valores dinámicos").build();
        options.addOption(dynamicFile);

        options.addOption("m",true, "Matriz de M x M");
        options.addOption("radius", true, "Radio de interacción de las partículas");
        options.addOption("periodic", false, "Condición de contorno periódicas");
        options.addOption("brute", false, "Corre el método de fuerza bruta");
        return options;
    }



    public static void parseArguments(String[] args, Properties properties) throws IllegalArgumentException {
        Options options = createOptions();
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("m")) {
                properties.setM(Integer.parseInt(cmd.getOptionValue("m")));
            }

            if (cmd.hasOption("radius")) {
                properties.setR_c(Float.parseFloat(cmd.getOptionValue("radius")));
            }

            if(cmd.hasOption("periodic")) {
                properties.setPeriodic(true);
            }

            if(cmd.hasOption("brute")){
                properties.setBrute(true);
            }

            dynamicFile = cmd.getOptionValue("dynamicFile");
            staticFile = cmd.getOptionValue("staticFile");


        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helper.printHelp("Modo de uso:", options);
            System.exit(0);
        }
    }
}
