/**
 *
 * @author Hugo
 */

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

function toggleForm(img, event, target)
{
    event.preventDefault();
    img.src = (img.src.match("/interface_admin/static/images/arrow_up.png")) ?
        "/interface_admin/static/images/arrow_down.png" : "/interface_admin/static/images/arrow_up.png";
    $(document.getElementById(target)).slideToggle(350);
}