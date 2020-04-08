package utils

import log.LogFacade
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException

final class JsonUtils {

    private static final JSONParser PARSER = new JSONParser()

    static void writeJsonToFile(JSONObject json, File file) {
        file.withPrintWriter { writer ->
            json.writeJSONString(writer)
        }
    }

    static JSONObject readJsonFromFile(File file) {
        try {
            return PARSER.parse(file.newReader()) as JSONObject
        } catch (ParseException exception) {
            LogFacade.ERROR("File can not be parsed", exception)
        }
        return null
    }
}