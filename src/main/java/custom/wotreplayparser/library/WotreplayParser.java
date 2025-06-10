package custom.wotreplayparser.library;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WotreplayParser {
    private String metadata;
    private int metadataLength;
    private String personalStats;
    private int personalStatsLength;
    public WotreplayParser(){}
    public String getStringMetadata(){
        return this.metadata;
    }
    public String getStringPersonalStats(){
        return this.personalStats;
    }
    public JsonElement getJsonNodeJacksonMetadata(){
        JsonElement jsonElement = null;
        try {
            jsonElement = JsonParser.parseString(this.metadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonElement;
    }

    public JsonElement getJsonNodeJacksonPersonalStats(){
        JsonElement jsonElement = null;
        try {
            jsonElement = JsonParser.parseString(this.personalStats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonElement;
    }
    public void parse(int metadataByteLengthStartPos, int metadataByteLength, int personalStatsJsonOffset, String filePath) throws IOException {
        /// 07/09/2024
        /// world of tanks version: 1.26.0.4684
        /// .wotreplay files architecture at this moment
        /// [8 bytes of something][4 bytes describing the length of the following json data][{metadata json }][4 bytes describing the length of the following json data][{battle stats json }]

        byte[] fileData = Files.readAllBytes(Paths.get(filePath));
        System.out.println(filePath);
        System.out.println(extractlilEndianInt(fileData, 0, 8));

        this.metadataLength = extractlilEndianInt(fileData , metadataByteLengthStartPos, metadataByteLength);

        int startOfMetadata = metadataByteLengthStartPos + metadataByteLength;
        this.metadata = extractString(fileData , startOfMetadata, this.metadataLength);

        int secondStart = metadataLength + startOfMetadata + personalStatsJsonOffset;

        this.personalStatsLength = extractlilEndianInt(fileData, secondStart - personalStatsJsonOffset, personalStatsJsonOffset);

        try {
            this.personalStats = extractString(fileData, secondStart, this.personalStatsLength);
        } catch (StringIndexOutOfBoundsException e){
            this.personalStats = "";
        }
    }

    public void parse(String filePath) throws IOException {
        this.parse(8, 4, 4, filePath);
    }

    private static int extractlilEndianInt(byte[] data, int start, int length) {
        int value = 0;
        for (int i = 0; i < length; i++) {
            value |= (data[start + i] & 0xFF) << (8 * i);
        }
        return value;
    }

    private static String extractString(byte[] data, int start, int length) throws StringIndexOutOfBoundsException{
        return new String(data, start, length, StandardCharsets.UTF_8);
    }
}
