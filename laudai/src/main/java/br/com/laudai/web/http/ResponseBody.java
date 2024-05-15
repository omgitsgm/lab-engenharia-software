package br.com.laudai.web.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseBody {

    private Integer httpStatusCode;
    private String title;
    private List<Object> results;

}
