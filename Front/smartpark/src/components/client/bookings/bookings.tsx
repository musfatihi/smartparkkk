import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {Button} from "primereact/button";
import {useEffect, useRef, useState} from "react";
import {Booking} from "../../../intrfcs/booking/booking";
import ApiBooking from "../../../redux/api/ApiBooking";
// @ts-ignore
import saveAs from "file-saver";
import html2canvas from "html2canvas";
import Barcode from "react-barcode";

const MyBookings: React.FC = () => {

    const [bookings, setBookings] = useState<Booking[]>([])

    const barcodeRef = useRef<HTMLDivElement>(null); // Ref to barcode element
    const [downloadUrl, setDownloadUrl] = useState<string | null>(null);

    const downloadBarcode = async () => {
        if (!barcodeRef.current) return;

        const canvas = await html2canvas(barcodeRef.current); // Capture as canvas
        const imageData = canvas.toDataURL('image/png'); // Get PNG data URL
        setDownloadUrl(imageData); // Store URL for download

        console.log(imageData)
    };

    useEffect(() => {
        ApiBooking.getBookings().then(r =>
        {
            console.log(r);
            setBookings(r);

        })
    }, []);




    /*
    const generateQrCode = (data:any) => {
        const jsonData = JSON.stringify(data); // Stringify the JSON

        setQrCodeUrl(jsonData); // Set the URL for the QR code

        if (!qrCodeUrl) return; // Handle empty URL case

        const svgString = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256">${qrCodeUrl}</svg>`;
        const blob = new Blob([svgString], { type: 'image/svg+xml' });

        saveAs(blob, 'qr-code.svg'); // Use file-saver for saving

    };
     */

    const generateQrCode = (data:any) => {


    };


    return (
        <div className="card" style={{border: 'none', display: 'block'}}>

            {bookings.length>0 &&
                (<DataTable value={bookings} loading={false} loadingIcon="pi pi-spinner" paginator rows={5}
                            rowsPerPageOptions={[5, 10, 25, 50]}
                            paginatorTemplate="FirstPageLink PrevPageLink CurrentPageReport NextPageLink LastPageLink RowsPerPageDropdown"
                            currentPageReportTemplate="{first} to {last} of {totalRecords}">
                    <Column field="parkingSpaceNbr" header="parkingSpaceNbr"></Column>
                    <Column field="floorNbr" header="floorNbr"></Column>
                    <Column field="parkingName" header="parkingName"></Column>
                    <Column header="Actions" headerStyle={{width: '8rem'}} body={(rowData: Booking) => (

                        <div className='flex'>
                            <div className="invisible" ref={barcodeRef}>
                                <Barcode value={JSON.stringify(rowData)}  width={2} height={50}/>
                            </div>
                            {downloadUrl && (
                                <a href={downloadUrl} download="barcode.png">
                                    Download Barcode
                                </a>
                            )}
                            <Button icon="pi pi-trash" className="p-button-rounded p-button-danger text-red-500"
                                    onClick={downloadBarcode}/>
                        </div>
                    )}></Column>
                </DataTable>)
            }
        </div>
    );
};

export default MyBookings;
