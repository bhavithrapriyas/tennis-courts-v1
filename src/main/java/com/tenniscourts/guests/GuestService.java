package main.java.com.tenniscourts.guests;

import main.java.com.tenniscourts.guests.GuestDTO;
import main.java.com.tenniscourts.exceptions.EntityNotFoundException;
import main.java.com.tenniscourts.guests.GuestMapper;
import main.java.com.tenniscourts.guests.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public GuestDTO addGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.save(guestMapper.map(guestDTO)));
    }

    public GuestDTO updateGuest(GuestDTO guestDTO) {
        GuestDTO guest = guestRepository.findById(guestDTO.getId()).map(guestMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        });

        guest.setName(guestDTO.getName());

        return guestMapper.map(guestRepository.save(guestMapper.map(guest)));
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public GuestDTO findGuestById(Long id) {
        return guestMapper.map(
            guestRepository.findById(id).orElseThrow(() -> {
                throw new EntityNotFoundException("Guest not found.");
            })
        );
    }

    public List<GuestDTO> findGuestByName(String name) {
        return guestRepository.findByGuest_NameOrderByName(name).stream().map(guestMapper::map).collect(Collectors.toList());
    }

    public List<GuestDTO> findGuestAll() {
        return guestRepository.findAll().stream().map(guestMapper::map).collect(Collectors.toList());
    }
}
