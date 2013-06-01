function validatePhone()
{
    if(event.keyCode >= 48 && event.keyCode <= 57)
        event.returnValue = true;
    else
        event.returnValue = false;
}
