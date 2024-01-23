package br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator;

import java.io.IOException;
import java.util.List;

public interface convetJson {
    <T> T convert(List<String> obj , Class<T> classe) throws IOException;
}
