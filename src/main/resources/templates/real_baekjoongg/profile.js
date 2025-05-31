const ctxPie = document.getElementById('pieChart').getContext('2d');
new Chart(ctxPie, {
  type: 'doughnut',
  data: {
    labels: ['Bronze', 'Silver', 'Gold', 'Platinum', 'Diamond', 'Ruby'],
    datasets: [{
      label: '문제 수',
      data: [101, 23, 1, 0, 0, 0],
      backgroundColor: [
        '#B87333',
        '#C0C0C0',
        '#FFD700',
        '#8EF',   
        '#00F',   
        '#F00'    
      ],
      borderWidth: 1
    }]
  },
  options: {
    plugins: {
      legend: {
        position: 'bottom'
      }
    }
  }
});

const ctxStreak = document.getElementById('streakChart').getContext('2d');
new Chart(ctxStreak, {
  type: 'bar',
  data: {
    labels: ['3월', '4월', '5월'],
    datasets: [{
      label: '문제 푼 날짜 수',
      data: [10, 14, 2],
      backgroundColor: '#350FC0'
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    },
    plugins: {
      legend: {
        display: false
      }
    }
  }
});

const grassCtx = document.getElementById('grassChart').getContext('2d');

const grassData = [];
const labels = [];

for (let i = 0; i < 70; i++) {
  grassData.push(Math.floor(Math.random() * 6));
  labels.push('');
}

new Chart(grassCtx, {
  type: 'matrix',
  data: {
    datasets: [{
      label: '풀었는지 여부',
      data: labels.map((_, i) => ({
        x: i % 10,
        y: Math.floor(i / 10),
        v: grassData[i]
      })),
      backgroundColor(ctx) {
  const value = ctx.dataset.data[ctx.dataIndex].v;

  const levels = [
    '#eeeeee', 
    '#d6dbff', 
    '#b0baff', 
    '#8a98ff', 
    '#6476ff', 
    '#350FC0'  
  ];

  return levels[Math.min(value, 5)];
}
,
      width: ({ chart }) => (chart.chartArea || {}).width / 10 - 2,
      height: ({ chart }) => (chart.chartArea || {}).height / 7 - 2,
    }]
  },
  options: {
    plugins: {
      legend: { display: false }
    },
    scales: {
      x: {
        display: false,
        min: 0,
        max: 10
      },
      y: {
        display: false,
        min: 0,
        max: 7
      }
    }
  }
});

function toggleRanking(type) {
  const display = document.getElementById('ranking-display');
  if (type === 'today') {
    display.innerHTML = `
      <ul>
        <li>📈 오늘 1등과의 문제 수 차이: 3문제</li>
        <li>🔥 오늘 푼 문제 수: 7문제</li>
        <li>🎯 오늘 랭킹: 24등</li>
      </ul>
    `;
  } else if (type === 'other') {
    display.innerHTML = `
      <ul>
        <li>💯 누적 푼 문제 수: 125문제</li>
        <li>⭐ 최고 등급 문제: Gold</li>
        <li>📆 가입일: 2024-03-14</li>
      </ul>
    `;
  } else if (type === 'change') {
    display.innerHTML = `
      <ul>
        <li>📊 지난주 대비 랭킹 상승: +5</li>
        <li>🚀 일일 평균 문제 풀이 수: 3.4</li>
        <li>🔄 최근 7일 연속 문제 풀이</li>
      </ul>
    `;
  }
}
