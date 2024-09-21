package model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilter {
    private Integer page;
    private Integer size;
    private String keyWord;
}
