import {useSelector} from "react-redux";
import {RootState} from "../../../redux/reducers/RootState";
import React, {useEffect, useState} from "react";
import {Floor} from "../../../intrfcs/floor/floor";
import {useAppDispatch} from "../../../redux/store/store";
import {deleteFloor, getFloors} from "../../../redux/actions/floorActions";
import {Button} from "primereact/button";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import FloorDialog from "./FloorDialog";


const Floors: React.FC = () => {

    const { floors, loading, error } = useSelector((state: RootState) => state.floor);
    const [dialogVisible, setDialogVisible] = useState<boolean>(false);
    const [selectedFloor, setSelectedFloor] = useState<Floor | null>(null);
    const dispatch = useAppDispatch();

    useEffect(() => {
        dispatch(getFloors());
    }, [dispatch]);


    const handleUpdateFloor = (floor: Floor) => {
        setSelectedFloor(floor);
        setDialogVisible(true);
    }

    const handleDeleteFloor = (floorId: string) => {
        dispatch(deleteFloor(floorId));
    }
    const refreshData = async () => {
        await dispatch(getFloors());

    }
    return (
        <div className="card" style={{ border: 'none' , display: 'block' }}>

            <Button label="Add Floor" className="bg-blue-500 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline flex mx-auto mt-5 mb-5" onClick={() => { setSelectedFloor(null); setDialogVisible(true); }} />
            <FloorDialog visible={dialogVisible} onHide={() => { setDialogVisible(false); setSelectedFloor(null); }} refreshData={refreshData} selectedFloor={selectedFloor} />
            <DataTable value={floors} loading={loading} loadingIcon="pi pi-spinner" paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]}
                       paginatorTemplate="FirstPageLink PrevPageLink CurrentPageReport NextPageLink LastPageLink RowsPerPageDropdown"
                       currentPageReportTemplate="{first} to {last} of {totalRecords}">
                <Column field="parkingName" header="PARKING Name"></Column>
                <Column field="nbr" header="Floor"></Column>
                <Column header="Actions" headerStyle={{ width: '8rem' }} body={(rowData: Floor) => (

                    <div className='flex'>
                        <Button icon="pi pi-pencil" className="p-button-rounded p-button-info mr-2" onClick={() => handleUpdateFloor(rowData)} />
                        <Button icon="pi pi-trash" className="p-button-rounded p-button-danger text-red-500" onClick={() => handleDeleteFloor(rowData.id)} />
                    </div>
                )}></Column>

            </DataTable>
            {error && <div className="text-red-500">{error}</div>}
        </div>
    );
};

export default Floors;
