/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author huynh
 */
public class LoggingUtility {

    public static void writeLog(String file, String msg) {
        Logger logger = Logger.getLogger("SimpleBlog_Log");
        FileHandler fh;
        try {
            fh = new FileHandler("./test/SB_LogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.log(Level.INFO, "{0} - {1}", new Object[]{file, msg});
        } catch (SecurityException | IOException e) {
        }
    }
}
