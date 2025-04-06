package org.untoc_camp.dto.ranking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolvedAcUserStatsDto {
    private Long rating;
    private int tier;
    private int rank;
    private int solvedCount;
}
