document.addEventListener("DOMContentLoaded", async () => {
  try {
    // nickname은 html에서 th:inline으로 주입됨
    if (!nickname) throw new Error("닉네임 정보를 가져올 수 없습니다.");

    // 1. 유저 기본 정보 가져오기
    const res = await fetch(`/userinfo/${nickname}`);
    if (!res.ok) throw new Error("유저 정보를 불러올 수 없습니다.");
    const user = await res.json();

    document.getElementById("nickname").textContent = user.name;
    document.getElementById("tier").textContent = user.tier;
    document.getElementById("solved-count").textContent = `총 ${user.totalSolved}문제 풀었어요`;

    const bojId = user.bojId;

    // 2. 잔디 이미지
    const strick = document.getElementById("strick");
    strick.src = `http://mazandi.herokuapp.com/api?handle=${bojId}&theme=warm`;

    // 5. 목표 관련 API
    const goalRes = await fetch(`/boj/daily-goal/${bojId}`);
    if (goalRes.ok) {
      const goalData = await goalRes.json();
      document.getElementById("daily-goal").textContent = `✅ ${goalData.message}`;
    }

    // 스트릭 테마 변경
    window.toggleStrick = (theme) => {
      strick.src = `http://mazandi.herokuapp.com/api?handle=${bojId}&theme=${theme.toLowerCase()}`;
    };

  } catch (err) {
    alert(err.message);
  }
});

function getColor(level) {
  const colors = {
    Bronze: "#B87333",
    Silver: "#C0C0C0",
    Gold: "#FFD700",
    Platinum: "#8EF",
    Diamond: "#00F",
    Ruby: "#F00"
  };
  return colors[level] || "#000";
}

async function toggleRanking(type) {
  const display = document.getElementById('ranking-display');

  try {
    const res = await fetch(`/userinfo/${nickname}/ranking`);
    if (!res.ok) throw new Error("랭킹 정보를 불러올 수 없습니다.");

    const data = await res.json();

    let html = "";

    if (type === "today" && data.day) {
      html = `
        <ul>
          <li>📈 오늘 오른 점수: ${data.day.rating >= 0 ? "+" : ""}${data.day.rating}</li>
          <li>🔥 오늘 푼 문제 수: ${data.day.solvedCount}</li>
        </ul>`;
    } else if (type === "weekly" && data.week) {
      html = `
        <ul>
          <li>📈 일주일 간 오른 점수: ${data.week.rating >= 0 ? "+" : ""}${data.week.rating}</li>
          <li>🔥 일주일 간 푼 문제 수: ${data.week.solvedCount}</li>
        </ul>`;
    } else if (type === "total" && data.total) {
      html = `
        <ul>
          <li>📈 총 점수: ${data.total.rating}</li>
          <li>🔥 총 푼 문제 수: ${data.total.solvedCount}</li>
        </ul>`;
    } else {
      html = "정보 없음";
    }

    display.innerHTML = html;

  } catch (error) {
    display.innerHTML = `<p style="color: red;">${error.message}</p>`;
  }
}
