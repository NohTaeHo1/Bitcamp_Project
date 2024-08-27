import { PG } from "@/app/components/common/enums/PG";
import { OfficetelModel } from "@/app/components/officetel/model/officetel-model";
import Link from "next/link";
import { useEffect, useRef } from "react";

export default function CardButton({
  id,
  buildingName,
  propertyType,
  price,
  monthlyRent,
  area,
  floor,
  description,
  listingDate,
}: OfficetelModel) {
  const cardRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (cardRef.current) {
      cardRef.current.style.height = `${cardRef.current.scrollHeight}px`;
    }
  }, []);

  return (
    <div
      ref={cardRef}
      className="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 m-4"
    >
      <div className="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 m-4">
        <div className="p-5">
          <p>
            <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
              {buildingName}
            </h5>
          </p>
          <div style={{ display: "flex", justifyContent: "center" }}>
            <svg
              className="rtl:rotate-180 w-20 h-20 ms-2"
              aria-hidden="true"
              fill="none"
              viewBox="0 0 14 10"
            >
              <image xlinkHref="/user/img/home.png" height="10" width="14" />
            </svg>
          </div>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            종류 :{" "}
            {propertyType === 1 ? "매매" : propertyType === 2 ? "월세" : "전세"}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            가격 : {price}
            
            {/* {typeof price === 'string' ? ((parseInt(price) >=100000000 )? parseInt(price)/100000000 + " 억" + parseInt(price)/10000 + " 만" + parseInt(price)%10000000 + " 원": 
            (parseInt(price) >=10000 )? parseInt(price)/10000 + " 만" + parseInt(price)%10000000 + " 원": parseInt(price)%10000000 + " 원") : '미정'} */}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            {monthlyRent !== null ? "월세 : " + monthlyRent : null}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            면적(m²) : {area}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            층수 : {floor}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            {description}
          </p>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            올린 날짜 : {listingDate}
          </p>
          <Link
            key={id}
            href={`${PG.OFFICETEL}/list/${id}`}
            className="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            자세히 보기
          </Link>
        </div>
      </div>
    </div>
  );
}
