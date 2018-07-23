package com.qulix.logger;
import org.apache.log4j.Logger;

public class Log {

        //Initialize Log4j instance
        private static Logger Log = Logger.getLogger(com.qulix.logger.Log.class.getName());

        public static void startLog (String testClassName){
            Log.info("Test is Starting.");
        }

        public static void endLog (String testClassName){
            Log.info("Test is Ending.");
        }

        //Info Level Logs
        public static void info (String message) {
            Log.info(message);
        }

        //Warn Level Logs
        public static void warn (String message) {
            Log.warn(message);
        }

        //Error Level Logs
        public static void error (String message) {
            Log.error(message);
        }

        //Fatal Level Logs
        public static void fatal (String message) {
            Log.fatal(message);
        }

        //Debug Level Logs
        public static void debug (String message) {
            Log.debug(message);
        }
}
