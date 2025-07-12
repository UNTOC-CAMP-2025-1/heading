document.addEventListener("DOMContentLoaded", () => {
    const top3Container = document.getElementById("top3-container");
    const tableBody = document.getElementById("ranking-table-body");
    const options = document.querySelectorAll(".ranking-option");

    const dummy = {
        bojId: "anonymous",
        rating: 0,
        tier: 0,
        solvedCount: 0
    };

    // ✅ 숫자 티어 → 문자열 티어 변환 (Total Ranking 전용)
    function convertTierToString(tier) {
        if (tier === 0) return "Unrated";

        const levels = ["Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"];
        const group = Math.floor((tier - 1) / 5);
        const level = 5 - ((tier - 1) % 5);

        if (group >= 0 && group < levels.length) {
            return `${levels[group]} ${level}`;
        } else {
            return "Unknown";
        }
    }

    // ✅ 공통 렌더링 함수 (type: "now" | "week" | "day")
    function renderRanking(dataList, type) {
        const top3 = dataList.slice(0, 3);
        while (top3.length < 3) top3.push(dummy);

        const medalClass = ["gold", "silver", "bronze"];
        top3Container.innerHTML = top3.map((user, idx) => `
            <div class="top3-card ${medalClass[idx]}">
                <img src="/img/profile.png" alt="Profile">
                <div class="username">${user.bojId}</div>
                <div class="points">${user.rating}점</div>
            </div>
        `).join("");

        tableBody.innerHTML = dataList.map((user, index) => `
            <tr>
                <td>${index + 1}위</td>
                <td class="pointcolor">${user.bojId}</td>
                <td>${type === "now" ? convertTierToString(user.tier) : user.tier ?? "-"}</td>
                <td>${user.solvedCount ?? "-"}</td>
                <td>${user.rating ?? "-"}</td>
            </tr>
        `).join("");
    }

    // ✅ 초기 렌더링 (total ranking)
    fetch("/ranking/ranking")
        .then(res => res.json())
        .then(json => renderRanking(json.nowData || [], "now"))
        .catch(err => console.error("초기 데이터 로드 실패", err));

    // ✅ 탭 전환
    options.forEach(option => {
        option.addEventListener("click", () => {
            options.forEach(o => o.classList.remove("active"));
            option.classList.add("active");

            const type = option.getAttribute("data-type"); // "day", "week", "now"

            fetch("/ranking/ranking")
                .then(res => res.json())
                .then(json => {
                    if (type === "day") {
                        renderRanking(json.dayData || [], "day");
                    } else if (type === "week") {
                        renderRanking(json.weekData || [], "week");
                    } else {
                        renderRanking(json.nowData || [], "now");
                    }
                })
                .catch(err => console.error("탭 변경 시 데이터 로드 실패", err));
        });
    });
});
