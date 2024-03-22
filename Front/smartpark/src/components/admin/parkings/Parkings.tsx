import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import "primereact/resources/themes/saga-blue/theme.css";
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import { Button } from 'primereact/button';

import ParkingDialog from './ParkingDialog';
import {RootState} from "../../../redux/reducers/RootState";
import {useAppDispatch} from "../../../redux/store/store";
import {deleteParking, getParkings} from "../../../redux/actions/parkingActions";
import {Parking} from "../../../intrfcs/parking/parking";

const Parkings: React.FC = () => {

    const { parkings, loading, error } = useSelector((state: RootState) => state.parking);
    const [dialogVisible, setDialogVisible] = useState<boolean>(false);
    const [selectedParking, setSelectedParking] = useState<Parking | null>(null);
    const dispatch = useAppDispatch();

    useEffect(() => {
        dispatch(getParkings());
    }, [dispatch]);


    const handleUpdateParking = (parking: Parking) => {
        setSelectedParking(parking);
        setDialogVisible(true);
    }

    const handleDeleteParking = (parkingId: string) => {
        dispatch(deleteParking(parkingId));
    }
    const refreshData = async () => {
        await dispatch(getParkings());

    }
    return (
        <div className="card" style={{ border: 'none' , display: 'block' }}>

            <Button label="Create Parking" className="bg-blue-500 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline flex mx-auto mt-5 mb-5" onClick={() => { setSelectedParking(null); setDialogVisible(true); }} />
            <ParkingDialog visible={dialogVisible} onHide={() => { setDialogVisible(false); setSelectedParking(null); }} refreshData={refreshData} selectedParking={selectedParking} />
            <DataTable value={parkings} loading={loading} loadingIcon="pi pi-spinner" paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]}
                       paginatorTemplate="FirstPageLink PrevPageLink CurrentPageReport NextPageLink LastPageLink RowsPerPageDropdown"
                       currentPageReportTemplate="{first} to {last} of {totalRecords}">
                <Column field="name" header="PARKING Name"></Column>
                <Column field="city" header="City"></Column>
                <Column header="Actions" headerStyle={{ width: '8rem' }} body={(rowData: Parking) => (

                    <div className='flex'>
                        <Button icon="pi pi-pencil" className="p-button-rounded p-button-info mr-2" onClick={() => handleUpdateParking(rowData)} />
                        <Button icon="pi pi-trash" className="p-button-rounded p-button-danger text-red-500" onClick={() => handleDeleteParking(rowData.id)} />
                    </div>
                )}></Column>

            </DataTable>
            {error && <div className="text-red-500">{error}</div>}
        </div>
    );
};

export default Parkings;
