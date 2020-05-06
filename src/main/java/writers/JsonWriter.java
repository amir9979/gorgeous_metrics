/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package writers;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import elements.Class;
import elements.Program;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author thaina
 */
public class JsonWriter {

    public static void generateMetricsJson(Program program, String out_dir) throws IOException {
        List<Class> classes = program.getClasses();
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();
        try (final JsonGenerator generator = factory.createGenerator(new File(out_dir + "/" + "_metrics.json"), JsonEncoding.UTF8)) {
            generator.setCodec(mapper);
            generator.writeStartObject();
            for (Class classe : classes) {
//                generator.writeStartObject();
//                generator.writeFieldName("name");
                generator.writeFieldName(classe.getName());
//                generator.writeFieldName("metrics");
                generator.writeObject(classe.getMetrics());
//                generator.writeEndObject();
            }
            generator.writeEndObject();
            generator.close();
        }
    }

    public static void generateQualityPropertiesJson(Program program, JsonGenerator generator) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        generator.setCodec(mapper);
        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeString(program.getName());
        generator.writeFieldName("attributes");
        generator.writeObject(program.getQualityAttributes());
        generator.writeEndObject();
    }

    public static void generateJsonFiles(Program program, String out_dir) throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator generator = factory.createGenerator(new File(out_dir + "\\programs_quality_atributtes.json"), JsonEncoding.UTF8)) {
            generator.writeStartArray();
            JsonWriter.generateQualityPropertiesJson(program, generator);
            JsonWriter.generateMetricsJson(program, out_dir);
            generator.writeEndArray();
            generator.close();
        }
    }

}
