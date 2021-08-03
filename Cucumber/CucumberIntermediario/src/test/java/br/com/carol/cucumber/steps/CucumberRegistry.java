package br.com.carol.cucumber.steps;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;
import io.cucumber.docstring.DocStringType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CucumberRegistry implements TypeRegistry {
    @Override
    public void defineParameterType(ParameterType<?> parameterType) {
        new ParameterType<>("data", ".*", Date.class, (String s) -> {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date retorno = format.parse(s);
                return retorno;
            } catch (ParseException e){
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void defineDocStringType(DocStringType docStringType) {

    }

    @Override
    public void defineDataTableType(DataTableType dataTableType) {

    }

    @Override
    public void setDefaultParameterTransformer(ParameterByTypeTransformer parameterByTypeTransformer) {

    }

    @Override
    public void setDefaultDataTableEntryTransformer(TableEntryByTypeTransformer tableEntryByTypeTransformer) {

    }

    @Override
    public void setDefaultDataTableCellTransformer(TableCellByTypeTransformer tableCellByTypeTransformer) {

    }
}
