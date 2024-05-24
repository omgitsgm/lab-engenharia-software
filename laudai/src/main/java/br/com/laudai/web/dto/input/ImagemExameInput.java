package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ImagemExameInput(

        @NotNull
        MultipartFile arquivo

) {
}
