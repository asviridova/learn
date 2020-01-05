package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "protocol")
public class Protocol {
    public static Integer RESULT_CODE_OK = 0;
    public static Integer RESULT_CODE_ERROR = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "filename", nullable = false, unique = false)
    private String filename;
    @Column(name = "uploaddate", nullable = false, unique = false)
    private Date uploaddate;
    @Column(name = "resulttext", nullable = true, unique = false)
    private String resulttext;
    @Column(name = "resultcode", nullable = false, unique = false)
    private Integer resultcode;

    public Protocol(String filename, Date uploaddate, String resulttext, Integer resultcode){
        this.filename = filename;
        this.uploaddate = uploaddate;
        this.resulttext = resulttext;
        this.resultcode = resultcode;
    }

}


