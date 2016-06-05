function check(formID) {
    var pattern = null;
        
    switch(formID){
        case 'surname':
            pattern =  new RegExp('(^[A-Z]?[a-z]{1,25}$)|(^[А-Я]?[а-я]{1,25}$)', '');
            break;

        case 'name':
            pattern =  new RegExp('(^[A-Z]?[a-z]{1,25}$)|(^[А-Я]?[а-я]{1,25}$)', '');
            break;

        case 'passportNumber':
            pattern =  new RegExp('(^[A_Z]{2}[0-9]{7}$)', '');
            break;

        case 'passportAuthority':
            pattern =  new RegExp('(^[A-Z]?[a-z]{1,100}$)|(^[А-Я]?[а-я]{1,100}$)', '');
            break;
        
        case 'login':
            pattern =  new RegExp('(^[a-zA-Z0-9]{3,15}$)', '');
            break;
                
        case 'passwword':
            pattern =  new RegExp('^[a-zA-Z0-9_\\*\\!\\^]{6,15}$', '');
            break;
            
        case 'email':
            pattern =  new RegExp('^[a-z0-9\\.]{3,25}@[a-z\\.]{3,10}\\.{1}[a-z]{2,5}$', '');
            break;
            
        case 'phone':
            if(document.getElementById(formID + 'Form').value == '') {
                document.getElementById(formID).style.color = 'green';
                return;
            }
            pattern =  new RegExp('^(\\+\\d{3}\\s\\d{2})?\\s?\\d{3}(-|\\s)?\\d{2}(-|\\s)?\\d{2}$', '');
            break;
                
        case 'address':
            pattern = new RegExp('^[A-Za-zА-Яа-я\\d\\s\\.,\\-]{1,250}$','');
            break;
    }
        
    inputStr = document.getElementById(formID + 'Form').value;
        
    if(pattern.test(inputStr)) {
        document.getElementById(formID).style.color = 'green';
    } else {
        document.getElementById(formID).style.color = 'red';
    }
}

function validate_date(formID) {
    var error = null;

    inputStr = document.getElementById(formID + 'Form').value;
    var arrD = value.split(".");
    arrD[1] -= 1;
    var d = new Date(arrD[2], arrD[1], arrD[0]);
    var now = new Date();

    switch(formID){
        case 'passportIssue':
            error = (d.getFullYear()-now.getFullYear()>0) ? true : false;
            break;

        case 'passportExpire':
            error = (d.getFullYear()-now.getFullYear()>0) ? true : false;
            break;

        case 'birthday':
            error = (now.getFullYear()-d.getFullYear()>18) ? true : false;
            break;

        
        
        case 'login':
            pattern =  new RegExp('(^[a-zA-Z0-9]{3,15}$)', '');
            break;

        case 'password':
            pattern =  new RegExp('^[a-zA-Z0-9_\\*\\!\\^]{6,15}$', '');
            break;

        case 'email':
            pattern =  new RegExp('^[a-z0-9\\.]{3,25}@[a-z\\.]{3,10}\\.{1}[a-z]{2,5}$', '');
            break;

        case 'phone':
            if(document.getElementById(formID + 'Form').value == '') {
                document.getElementById(formID).style.color = 'green';
                return;
            }
            pattern =  new RegExp('^(\\+\\d{3}\\s\\d{2})?\\s?\\d{3}(-|\\s)?\\d{2}(-|\\s)?\\d{2}$', '');
            break;

        case 'address':
            pattern = new RegExp('^[A-Za-zА-Яа-я\\d\\s\\.,\\-]{1,250}$','');
            break;
    }

    if(error == true) {
        document.getElementById(formID).style.color = 'green';
    } else {
        document.getElementById(formID).style.color = 'red';
    }
}

function showDate(formID){
    return document.getElementById(formID).value=new Date();
}

