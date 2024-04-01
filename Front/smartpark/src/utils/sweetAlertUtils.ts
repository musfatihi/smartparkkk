import Swal from 'sweetalert2';

export const showConfirmationAlert = (title : string, text : string) => {
        return Swal.fire({
        title: title,
        text: text,
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, confirm!',
    });
};

export const showSuccessAlert = (title : string, text : string) => {
    Swal.fire({
        title: title,
        text: text,
        icon: 'success',
        confirmButtonColor: '#3085d6',
    });
};