document.addEventListener("DOMContentLoaded", () => {
  const options = document.querySelectorAll(".ranking-option");
  const periods = document.querySelectorAll(".ranking-period span");

  options.forEach(option => {
    option.addEventListener("click", () => {
      options.forEach(o => o.classList.remove("active"));
      option.classList.add("active");
    });
  });

  periods.forEach(period => {
    period.addEventListener("click", () => {
      periods.forEach(p => p.classList.remove("selected"));
      period.classList.add("selected");
    });
  });
});