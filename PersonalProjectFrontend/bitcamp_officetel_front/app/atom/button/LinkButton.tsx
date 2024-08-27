import { PG } from "@/app/components/common/enums/PG";
import { jwtDecode } from "jwt-decode";
import Link from "next/link"
import { parseCookies } from "nookies";

interface ILinkButton{
    id:number,
    title: string,
    path: string
}

export default function LinkButton ({id, title, path}:ILinkButton) {
    return ( 

        <Link href={`${path}`}
                    className="text-white bg-gradient-to-br from-blue-500 
                    to-green-400 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-pink-200 
                    dark:focus:ring-pink-800 font-medium rounded-lg text-sm px-5 py-3 text-center ml-8 mb-5" aria-current="page">
                        {title}
            </Link>
    )}


  export  const linkButtonTitles =() => [
        {id:1, title:'부동산 목록', path:`${PG.OFFICETEL}/list`},
        {id:2, title:'아파트', path:`${PG.OFFICETEL}/list`}, 
        {id:3, title:'오피스텔', path:`${PG.OFFICETEL}/list`},
        {id:4, title:'마이페이지', path:`${PG.USER}/detail/`},
        {id:5, title:'내가 올린 매물', path:`${PG.OFFICETEL}/list/myofficetel/`},
        {id:6, title:'매물 등록', path:`${PG.OFFICETEL}/save`}, 

      ];


    export  const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];