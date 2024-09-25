import Swal from 'sweetalert2';

export const showAlert = ({ title, text, icon, confirmButtonText = 'OK', showCancelButton = false, cancelButtonText = 'Cancelar' }) => {
    return Swal.fire({
        title: title,
        text: text,
        icon: icon,
        confirmButtonText: confirmButtonText,
        showCancelButton: showCancelButton,
        cancelButtonText: cancelButtonText,
        customClass: {
            confirmButton: 'btn btn-primary',
            cancelButton: 'btn btn-secondary'
        },
        buttonsStyling: false
    });
};

export const showSuccessAlert = (text) => {
    return showAlert({
        title: '¡Éxito!',
        text: text,
        icon: 'success'
    });
};

export const showErrorAlert = (text) => {
    return showAlert({
        title: '¡Error!',
        text: text,
        icon: 'error'
    });
};

export const showWarningAlert = (text) => {
    return showAlert({
        title: 'Advertencia',
        text: text,
        icon: 'warning'
    });
};

export const showConfirmationAlert = (text, confirmButtonText = 'Confirmar') => {
    return showAlert({
        title: 'Confirmación',
        text: text,
        icon: 'question',
        confirmButtonText: confirmButtonText,
        showCancelButton: true,
        cancelButtonText: 'Cancelar'
    });
};
