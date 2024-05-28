package br.com.laudai.web.feign;

import br.com.laudai.web.feign.model.ModeloResultadoInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(name = "modelo-api", url = "${modelo-api.url}")
public interface ModeloFeignClient {

    @PostMapping(value = "/predict", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ModeloResultadoInput> getPredict(@RequestPart("file") MultipartFile file);


}
