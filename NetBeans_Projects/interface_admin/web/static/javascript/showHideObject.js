/**
 *
 * @author Hugo
 */

function showHideObject(box, target) 
{
    document.getElementById(target).style.display = box.checked ? "" : "none";
}

function disableObject(box, target, hide)
{
    var objBox = document.getElementById(box);
    var objTarget = document.getElementById(target);
    if(hide===1)
        objTarget.style.visibility = (objBox.checked==true) ? 'visible' : 'hidden';
    else
        objTarget.disabled = (objBox.checked==true) ? false : true;
    return true;
}
