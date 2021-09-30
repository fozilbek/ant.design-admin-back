package com.efa.windoor.admin.form;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class FileQuestionForm implements Serializable {
    private Long id;
    private String orgFileName;
    private String fileName;
    private MultipartFile file;
}
