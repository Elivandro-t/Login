package br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class COnverteJSon implements  convetJson{
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T convert(List<String> obj, Class<T> classe) throws IOException {
        return mapper.readValue((JsonParser) obj,classe);
    }
}
