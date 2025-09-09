document.addEventListener("DOMContentLoaded", function() {
  const tutorialBtn = document.getElementById("tutorial-button")
  const popup = document.getElementById("tutorial-popup");
  const closeBtn = document.getElementById("close-tutorial");

  tutorialBtn.addEventListener("click", function() {
    popup.style.display = "block";
  });

  closeBtn.addEventListener("click", function() {
    popup.style.display = "none";
  });
});