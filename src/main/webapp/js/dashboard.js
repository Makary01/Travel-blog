let sortForm = document.forms[0]
let applyButton = document.getElementById("applyButton");
let checkAllButton = document.getElementById("checkAll")
let uncheckAllButton = document.getElementById("unCheckAll")
let inputs = document.querySelectorAll('.types');
let alreadyCheckedTypes = [];

function checkAll() {
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].checked = true;
    }
}

function uncheckAll() {
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].checked = false;
    }
}

checkAllButton.addEventListener("click", function () {
    checkAll();
})
uncheckAllButton.addEventListener("click", function () {
    uncheckAll();
})

applyButton.addEventListener("click", function () {
    let types = "";
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].checked === true) {
            types = types + inputs[i].value + ",";
        }
    }
    types = types.slice(0, -1);

    document.location.search = ("types=" + types + "&orderBy=" + sortForm.elements["sortBy"].value + "&order=" + sortForm.elements["order"].value);

})


window.addEventListener('DOMContentLoaded', () => {
    let kvp = document.location.search.substr(1).split('&');
    for (let i = 0; i < kvp.length; i++) {
        if (kvp[i].startsWith("types=")) {
            alreadyCheckedTypes = kvp[i].substr(6).split(",");
            if (alreadyCheckedTypes.length > 0) {
                for (let j = 0; j < inputs.length; j++) {
                    if (alreadyCheckedTypes.includes(inputs[j].value)) {
                        inputs[j].checked = true;
                    }
                }
            }
        }
        if (kvp[i].startsWith("orderBy=")) {
            sortForm.elements["sortBy"].value = kvp[i].substr(8);
        }
        if (kvp[i].startsWith("order=")) {
            sortForm.elements["order"].value = alreadyCheckedTypes = kvp[i].substr(6);
        }
    }


});