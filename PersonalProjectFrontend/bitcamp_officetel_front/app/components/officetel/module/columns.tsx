import { Link, Typography } from "@mui/material";
import { GridColDef } from "@mui/x-data-grid";
import { MyTypography } from "../../common/style/cell";
import { PG } from "../../common/enums/PG";
import { useDispatch } from "react-redux";
import { useRouter } from "next/navigation";
import { OfficetelColumn } from "../model/officetel-columns";
import { deleteOfficetelsById, findOfficetelsById, findOfficetelsByUsername, modifyOfficetelById } from "../service/officetel.service";

interface CellType {
  row: OfficetelColumn;
}

export default function OfficetelColumns(): GridColDef[] {
  const dispatch = useDispatch();
  const router = useRouter();

  return [
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "buildingName",
      headerName: "건물 이름",
      align: "center",
      renderCell: ({ row }: CellType) =>
        MyTypography(row.buildingName, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "propertyType",
      headerName: "매물종류",
      renderCell: ({ row }: CellType) =>
        MyTypography(row.propertyType, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "area",
      headerName: "넓이(m²)",
      renderCell: ({ row }: CellType) => MyTypography(row.area, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "floor",
      headerName: "층수",
      renderCell: ({ row }: CellType) => MyTypography(row.floor, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "price",
      headerName: "가격(*월세는 전세금)",
      renderCell: ({ row }: CellType) => MyTypography(row.price, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "monthlyRent",
      headerName: "월세",
      renderCell: ({ row }: CellType) =>
        MyTypography(row.monthlyRent, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "owner",
      headerName: "판매자",
      renderCell: ({ row }: CellType) => MyTypography(row.owner, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "direction",
      headerName: "방향",
      renderCell: ({ row }: CellType) => MyTypography(row.direction, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "listingDate",
      headerName: "매물날짜",
      renderCell: ({ row }: CellType) =>
        MyTypography(row.listingDate, "1.2rem"),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "delete",
      headerName: "DELETE",
      renderCell: ({ row }: CellType) => (
        <div
          style={{ cursor: "pointer", textDecoration: "underline" }}
          className="btn underline-offset-4 
            focus:outline-none focus:ring focus:ring-violet-300
            overflow-hidden relative w-full h-full font-bold -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
            before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-300"
          onClick={() => {
            confirm("해당 오피스텔을 삭제합니다.");
            dispatch(deleteOfficetelsById(row.id));
            dispatch(findOfficetelsByUsername())
            window.location.reload(); //새로고침
          }}
        >
          {" "}
          Delete
        </div>
      ),
    },
    {
      flex: 0.04,
      minWidth: 30,
      sortable: false,
      field: "modify",
      headerName: "MODIFY",
      renderCell: ({ row }: CellType) => (
        <div
          style={{ cursor: "pointer", textDecoration: "underline", alignContent: "center" }}
          className="btn underline-offset-4 
            focus:outline-none focus:ring focus:ring-violet-300
            overflow-hidden relative w-full h-full font-bold -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
            before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-300"
          onClick={() => {
            confirm("해당 오피스텔을 수정합니다.");
            router.push(`${PG.OFFICETEL}/modify/${row.id}`)


          }}
        >
          {" "}
          Modify
        </div>
      ),
    },
  ];
}
