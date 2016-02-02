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
        case '/ProyectoModulo5/public/Login.jsf':
            return '#li_login';
            break;
        case '/ProyectoModulo5/public/Register.jsf':
            return '#li_register';
            break;
        case '/ProyectoModulo5/private/MyAccount.jsf':
            return '#li_myaccount';
            break;
        case '/ProyectoModulo5/private/EditInformation.jsf':
            return '#li_edit_information';
        case '/ProyectoModulo5/private/UploadImg.jsf':
            return '#li_upload_img';
            break;
        case '/ProyectoModulo5/private/DownloadImg.jsf':
            return '#li_download_img';
            break;
        case '/ProyectoModulo5/private/DeleteImg.jsf':
            return '#li_delete_img';
            break;
        default:
            return null;
            break; 
    }
}