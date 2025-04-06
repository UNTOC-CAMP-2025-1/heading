package org.untoc_camp.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;

@Data
@AllArgsConstructor(staticName = "of")
public class ComparisonUserDto {
    private String username;
    private String bojId;
    private long ratingDiff;
    private int tierDiff;
    private int rankDiff;
    private int solvedCountDiff;

    public static ComparisonUserDto of(User user, UserBojInfo current, UserBojInfo base) {
        return new ComparisonUserDto(
                user.getUsername(),
                user.getBojId(),
                current.getRating() - base.getRating(),
                current.getTier() - base.getTier(),
                current.getRank() - base.getRank(),
                current.getSolvedCount() - base.getSolvedCount()
        );
    }
}