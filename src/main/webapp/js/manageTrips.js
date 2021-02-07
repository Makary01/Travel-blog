let sortByIdButton = document.getElementById("sortById")
let sortByCreatedButton = document.getElementById("sortByCreated")
let sortByTitleButton = document.getElementById("sortByTitle")
let sortByStartDateButton = document.getElementById("sortByStartDate")
let sortByEndDateButton = document.getElementById("sortByEndDate")
let orderAsc = document.getElementById("orderAsc")
let orderDesc = document.getElementById("orderDesc")



function insertParam(key, value) {
    key = encodeURIComponent(key);
    value = encodeURIComponent(value);

    // kvp looks like ['key1=value1', 'key2=value2', ...]
    let kvp = document.location.search.substr(1).split('&');
    let i=0;

    for(; i<kvp.length; i++){
        if (kvp[i].startsWith(key + '=')) {
            let pair = kvp[i].split('=');
            pair[1] = value;
            kvp[i] = pair.join('=');
            break;
        }
    }

    if(i >= kvp.length){
        kvp[kvp.length] = [key,value].join('=');
    }

    // can return this
    let params = kvp.join('&');
    // or...

    // reload page with new params
    document.location.search = params;
}


sortByIdButton.addEventListener("click",function (){
    insertParam("orderBy", "id");
})
sortByCreatedButton.addEventListener("click",function (){
    insertParam('orderBy', "created");
})
sortByTitleButton.addEventListener("click",function (){
    insertParam('orderBy', "title");
})
sortByStartDateButton.addEventListener("click",function (){
    insertParam('orderBy', "startDate");
})
sortByEndDateButton.addEventListener("click",function (){
    insertParam('orderBy', "endDate");
})
orderAsc.addEventListener("click",function (){
    insertParam('order', "asc");
})
orderDesc.addEventListener("click",function (){
    insertParam('order', "desc");
})

