function sundayDisabled(date)
{
    var day = date.getDay();
    return [day != 0,'']
}