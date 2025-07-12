package org.untoc_camp.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;

@Data
@AllArgsConstructor(staticName = "of")
public class ComparisonUserDto {
    private String bojId;
    private long rating;
    private int tier;
    private int rank;
    private int solvedCount;

    public static ComparisonUserDto of(User user, UserBojInfo current, UserBojInfo base) {
        long baseRating = base != null ? base.getRating() : 0;
        int baseTier = base != null ? base.getTier() : 0;
        int baseRank = base != null ? base.getRank() : 0;
        int baseSolved = base != null ? base.getSolvedCount() : 0;

        return new ComparisonUserDto(
                user.getBojId(),
                current.getRating() - baseRating,
                current.getTier() - baseTier,
                current.getRank() - baseRank,
                current.getSolvedCount() - baseSolved
        );
    }

}