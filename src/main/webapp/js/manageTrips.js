function insertParam(key, value) {
    key = encodeURIComponent(key);
    value = encodeURIComponent(value);

    // kvp looks like ['key1=value1', 'key2=value2', ...]
    let kvp = document.location.search.substr(1).split('&');
    let i = 0;

    for (; i < kvp.length; i++) {
        if (kvp[i].startsWith(key + '=')) {
            let pair = kvp[i].split('=');
            pair[1] = value;
            kvp[i] = pair.join('=');
            break;
        }
    }

    if (i >= kvp.length) {
        kvp[kvp.length] = [key, value].join('=');
    }

    // can return this
    let params = kvp.join('&');
    // or...

    // reload page with new params
    document.location.search = params;
}


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
    let i = 0;
    for (; i < kvp.length; i++) {
        if (kvp[i].startsWith("types=")) {
            alreadyCheckedTypes = kvp[i].substr(6).split(",");
            break;
        }
    }

    if (alreadyCheckedTypes.length > 0) {

        for (let i = 0; i < inputs.length; i++) {
            if (!alreadyCheckedTypes.includes(inputs[i].value)) {
                inputs[i].checked = false;
            }
        }
    }
});
























