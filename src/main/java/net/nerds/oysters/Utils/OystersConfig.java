package net.nerds.oysters.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class OystersConfig {

    private static Logger logger = LogManager.getLogger();
    private String oysterJsonLocation = FabricLoader.getInstance().getConfigDirectory().getPath() + "/oysters.json";
    private File configFile = new File(oysterJsonLocation);
    private JsonObject config;
    private Gson gson;

    public void loadConfigs() {
        try {
            FileReader fileReader = new FileReader(oysterJsonLocation);
            setConfigs(fileReader);
        } catch (FileNotFoundException e) {
            buildFile();
        }
    }

    public void buildFile() {
        JsonObject jsonObject = getDefaults();
        try {
            FileWriter writer = new FileWriter(oysterJsonLocation);
            writer.write(jsonObject.toString());
            writer.flush();
        } catch (IOException e) {
            logger.fatal("Config IO Error", e);
        }
        config = jsonObject;
    }

    private void setConfigs(FileReader fileReader) {
        JsonParser parser = new JsonParser();
        config = parser.parse(fileReader).getAsJsonObject();
    }


    private JsonObject getDefaults() {
        JsonObject oysterDefaults = new JsonObject();
        oysterDefaults.add(OysterConfigValues.BASE_OYSTER_TIME, new JsonPrimitive(1500));
        oysterDefaults.add(OysterConfigValues.BASE_BASKET_PEARL_GEN_TIME, new JsonPrimitive(1500));
        oysterDefaults.add(OysterConfigValues.BASE_BASKET_MUTATE_TIME, new JsonPrimitive(2000));
        oysterDefaults.add(OysterConfigValues.MUTATION_CHANCE, new JsonPrimitive(4));
        return oysterDefaults;
    }

    public int getProperty(String key) {
        try{
            return config.get(key).getAsInt();
        } catch (NumberFormatException nex) {
            logger.error("Can not Format the value you entered for {} into a number.  reverting to default", key);
            return getDefaults().get(key).getAsInt();
        } catch (Exception ex) {
            logger.error("Can not Format the value you entered for {} into a number..  reverting to default", key);
            return getDefaults().get(key).getAsInt();
        }
    }

    public boolean getBooleanProperty(String key) {
        try{
            return config.get(key).getAsBoolean();
        } catch (UnsupportedOperationException ex) {
            logger.error("Can not Format the value you entered for {} into a boolean..  reverting to default", key);
            return getDefaults().get(key).getAsBoolean();
        } catch (Exception ex) {
            logger.error("Can not Format the value you entered for {} into a boolean..  reverting to default", key);
            return getDefaults().get(key).getAsBoolean();
        }
    }
}
