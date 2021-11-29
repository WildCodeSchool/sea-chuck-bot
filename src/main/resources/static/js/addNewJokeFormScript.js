//addNewJokeFormScript.js

function yesVisibility() {
  if (document.getElementById("check").checked == true) {
      document.getElementById("yes").style.visibility = "visible";
      }
  else {
      document.getElementById("yes").style.visibility = "hidden";
      }
  }

function validateJoke() {
  let x = document.forms["adJoke"]["jokeText"].value;
  if (x == "") {
    alert("and says and says and says and is keeping on sayig!\n\nBUT: It doesn't matter what the page your host says !!\n\nIt DOES matter what CHUCK says !!\nAnd HE HIMSELF says:\n\nYOU'RE A COWARD !! -- THE JOKE IS MISSING !!");
    return false;
  }
}