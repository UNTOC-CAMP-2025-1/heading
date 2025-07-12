package org.untoc_camp.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.untoc_camp.entity.CurrentUserBojInfo;

import java.util.List;

@Data
@AllArgsConstructor
public class ComparisonResultDto {
    private List<ComparisonUserDto> dayData;
    private List<ComparisonUserDto> weekData;
    private List<CurrentUserBojInfo> nowData;
}
