$( document ).ready(function() 
{
    var path = window.location.pathname;
    var idElement = getIdNavbarElement(path);
    if(idElement !== null)
    {
        var element = document.querySelector(idElement);
        element.className = "active";
    }
});

function getIdNavbarElement(path)
{
    switch (path)
    {
        case '/ProyectoModulo5/public/Home.jsf':
            return '#li_home';
            break;
        case '/ProyectoModulo5/public/Login.jsf':
            return '#li_login';
            break;
        case '/ProyectoModulo5/public/Register.jsf':
            return '#li_register';
            break;
        default:
            return null;
            break; 
    }
}