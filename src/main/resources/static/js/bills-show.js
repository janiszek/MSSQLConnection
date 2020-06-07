//hide the search button
function hideSearchButton(){
    document.querySelector(".button-violet").style.visibility = "hidden";
    // find the filter comboboxes and execute the click of the SEARCH button
    var selects = document.querySelectorAll(".slt-item");
    selects.forEach(function(tileselect){
        tileselect.addEventListener('change', function (event) {
            //alert("Wybrano z listy!");
            document.querySelector(".button-violet").click();
        });
    });
}

//implementation of OnClick of the table row
function addRowHandlers() {
    var table = document.getElementById("tableBills");
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
        var currentRow = table.rows[i];
        var createClickHandler = function(row) {
            return function() {
            alert("Select a proper button to Add new, Edit or Delete this bill");
            };
        };
        currentRow.onclick = createClickHandler(currentRow);
    }
}

// add confirmation dialog for btn-danger, only delete when confirmed
function addConfirmDelete(){
    var buttons = document.querySelectorAll(".btn-danger");
    buttons.forEach(function(tilebutton){
        tilebutton.addEventListener('click', function (event) {
        if (confirm("Are you sure to delete?")==false)
            event.preventDefault();
        });
    });
}

hideSearchButton();
addRowHandlers();
addConfirmDelete();
