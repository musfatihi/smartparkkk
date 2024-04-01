import React, {useEffect, useState} from "react";
import {RootState} from "../../../redux/reducers/RootState";
import {useSelector} from "react-redux";
import {ParkingSpace} from "../../../intrfcs/parkingSpace/parkingspace";
import {useAppDispatch} from "../../../redux/store/store";
import {deleteParkingSpace, getParkingSpaces} from "../../../redux/actions/parkingSpaceActions";
import {Button} from "primereact/button";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import ParkingSpaceDialog from "./ParkingSpaceDialog";


const ParkingSpaces: React.FC = () => {

    const { parkingSpaces, loading, error } = useSelector((state: RootState) => state.parkingSpace);
    const [dialogVisible, setDialogVisible] = useState<boolean>(false);
    const [selectedParkingSpace, setSelectedParkingSpace] = useState<ParkingSpace | null>(null);
    const dispatch = useAppDispatch();

    useEffect(() => {
        dispatch(getParkingSpaces());
    }, [dispatch]);


    const handleUpdateParkingSpace = (parkingSpace: ParkingSpace) => {
        setSelectedParkingSpace(parkingSpace);
        setDialogVisible(true);
    }

    const handleDeleteParkingSpace = (parkingSpaceId: string) => {
        dispatch(deleteParkingSpace(parkingSpaceId));
    }
    const refreshData = async () => {
        await dispatch(getParkingSpaces());

    }
    return (
        <div className="card" style={{ border: 'none' , display: 'block' }}>

            <Button label="Add Parking Space" className="bg-blue-500 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline flex mx-auto mt-5 mb-5" onClick={() => { setSelectedParkingSpace(null); setDialogVisible(true); }} />
            <ParkingSpaceDialog visible={dialogVisible} onHide={() => { setDialogVisible(false); setSelectedParkingSpace(null); }} refreshData={refreshData} selectedParkingSpace={selectedParkingSpace} />
            <DataTable value={parkingSpaces} loading={loading} loadingIcon="pi pi-spinner" paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]}
                       paginatorTemplate="FirstPageLink PrevPageLink CurrentPageReport NextPageLink LastPageLink RowsPerPageDropdown"
                       currentPageReportTemplate="{first} to {last} of {totalRecords}">
                <Column field="nbr" header="Parking Space Nbr"></Column>
                <Column field="floorNbr" header="Floor NBR"></Column>
                <Column field="parkingName" header="Parking Name"></Column>
                <Column header="Actions" headerStyle={{ width: '8rem' }} body={(rowData: ParkingSpace) => (

                    <div className='flex'>
                        <Button icon="pi pi-pencil" className="p-button-rounded p-button-info mr-2" onClick={() => handleUpdateParkingSpace(rowData)} />
                        <Button icon="pi pi-trash" className="p-button-rounded p-button-danger text-red-500" onClick={() => handleDeleteParkingSpace(rowData.id)} />
                    </div>
                )}></Column>

            </DataTable>
            {error && <div className="text-red-500">{error}</div>}
        </div>
    );
};

export default ParkingSpaces;
