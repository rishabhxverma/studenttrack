// editStudent.js

// Function to handle showing the modal and populating it with the student's data
function showModal(student) {
    // Extracting student information from the clicked element
    var name = student.querySelector(".student__name").innerText.trim();
    var weight = student.getAttribute("data-weight");
    var height = student.getAttribute("data-height");
    var hairColor = student.getAttribute("data-hairColor");
    var gpa = student.getAttribute("data-gpa");

    // Populating the modal input fields with the student's data
    document.getElementById("name").value = name;
    document.getElementById("weight").value = weight;
    document.getElementById("height").value = height;
    document.getElementById("hairColor").value = hairColor;
    document.getElementById("gpa").value = gpa;

    // Displaying the modal
    modal.style.display = "block";
}

// Assigning event listeners to each student element to trigger the modal
var students = document.querySelectorAll("student");
students.forEach(function(student) {
    student.addEventListener("click", function() {
        showModal(student);
    });
});

// Function to handle saving changes to the server
// Function to handle saving changes by submitting the form
function saveChanges() {
    // Submit the form containing the updated student information
    document.getElementById("editForm").submit();
}


// Event listener for the close button in the modal
var span = document.getElementsByClassName("close")[0];
span.onclick = function() {
    modal.style.display = "none";
}

// Event listener to close the modal when clicking outside of it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
