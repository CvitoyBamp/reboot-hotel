package ru.reboot.hotel.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTrigger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin/rooms")
public class RoomsController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;
    @GetMapping
    public String getRoomsInspector(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms_admin";
    }

    @GetMapping("/table")
    @HxRequest
    public String getRoomsTable(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms_admin";
    }

    @PostMapping("/table/create")
    @HxTrigger("createRoom")
    @HxRequest
    public String createRoom(@ModelAttribute Room room) {
        roomService.createNewRoom(room);
        return "rooms_admin";
    }
    @PostMapping("/table/delete/{id}")
    @HxTrigger("deleteRoom")
    @HxRequest
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoomById(id);
        return "rooms_admin";
    }

    @GetMapping("/type")
    public String getRoomsTypesInspector(Model model) {
        return "roomsType_admin";
    }

    @GetMapping("/type/table")
    @HxRequest
    public String getRoomsTypesTable(Model model) {
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "components/rooms/roomsTypesTable";
    }

    @PostMapping("/type/create")
    @HxTrigger("createRoomsTypes")
    @HxRequest
    public String createRoomsTypes(@ModelAttribute RoomType roomType) {
        roomTypeService.saveRoomsType(roomType);
        return "roomsType_admin";
    }

    @PostMapping("/type/delete/{id}")
    @HxTrigger("deleteRoomsTypes")
    @HxRequest
    public String deleteRoomsTypes(@PathVariable String id) {
        roomTypeService.deleteRoomsType(id);
        return "roomsType_admin";
    }
}
